

# Initial setup and imports
import requests
from bs4 import BeautifulSoup
import csv
import re
import os
import time


# Constants and utility functions
BASE_URL = "https://lists.openwall.net/linux-cve-announce"
OUTPUT_FILE = "cve_commits.csv"
START_YEAR_MONTH = "2024/02"
END_YEAR_MONTH = "2025/06"

def month_range(start, end):
    start_year, start_month = map(int, start.split("/"))
    end_year, end_month = map(int, end.split("/"))

    months = []
    for year in range(start_year, end_year + 1):
        for month in range(1, 13):
            if (year == start_year and month < start_month) or (year == end_year and month > end_month):
                continue
            months.append(f"{year:04d}/{month:02d}")
    return months


# Parsing functions
def extract_commit_pairs(text):
    pairs = []

    # "Issue introduced ... commit XXX and fixed ... commit YYY"
    intro_fix = re.findall(
        r"Issue introduced.*?commit\s+([0-9a-f]{7,40}).*?fixed.*?commit\s+([0-9a-f]{7,40})",
        text,
        re.DOTALL | re.IGNORECASE,
    )
    pairs.extend(intro_fix)

    # "Fixed in ... with commit XXX"
    fixes = re.findall(r"Fixed.*?commit\s+([0-9a-f]{7,40})", text, re.IGNORECASE)

    known_fixes = {fix for _, fix in intro_fix}
    for fix in fixes:
        if fix not in known_fixes:
            pairs.append(("", fix))

    return pairs

def get_repository(text):
    match = re.search(r"https://git\.kernel\.org[^ \n\"]+", text)
    return match.group(0) if match else ""


# Web scraping functions
def parse_cve_page(url):
    try:
        res = requests.get(url, timeout=20)
        res.raise_for_status()
        soup = BeautifulSoup(res.text, "html.parser")
        raw_text = soup.get_text()

        cve_match = re.search(r"(CVE-\d{4}-\d+)", raw_text)
        if not cve_match:
            print(f"❌ Skipped: no CVE ID found in {url}")
            return []

        cve_id = cve_match.group(1)
        repo = get_repository(raw_text)
        pairs = extract_commit_pairs(raw_text)

        if not pairs:
            print(f"⚠️  No commits found for {cve_id} at {url}")
        else:
            print(f"✅ Parsed {cve_id} with {len(pairs)} commit pairs from {url}")

        return [
            {
                "url": url,
                "cve": cve_id,
                "introducing_commit": intro,
                "fix_commit": fix,
                "repository": repo
            }
            for intro, fix in pairs
        ]
    except Exception as e:
        print(f"❌ Error parsing {url}: {e}")
        return []

def get_daily_links(month_url):
    try:
        res = requests.get(month_url, timeout=10)
        res.raise_for_status()
        soup = BeautifulSoup(res.text, "html.parser")
        links = [
            f"{month_url}{a['href']}"
            for a in soup.find_all("a", href=True)
            if a.text.startswith("CVE-")
        ]
        return links
    except Exception as e:
        print(f"Error accessing {month_url}: {e}")
        return []


# File handling
def save_results(rows):
    file_exists = os.path.exists(OUTPUT_FILE)
    with open(OUTPUT_FILE, "a", newline="") as csvfile:
        writer = csv.DictWriter(csvfile, fieldnames=["url", "cve", "introducing_commit", "fix_commit", "repository"])
        if not file_exists:
            writer.writeheader()
        for row in rows:
            writer.writerow(row)


# Main function
def main():
    print(f"📁 Writing to: {os.path.abspath(OUTPUT_FILE)}")
    months = month_range(START_YEAR_MONTH, END_YEAR_MONTH)

    for month in months:
        print(f"\n📅 Month: {month}")
        month_url = f"{BASE_URL}/{month}/"
        links = get_daily_links(month_url)
        print(f"🔗 Found {len(links)} CVE links.")

        for url in links:
            rows = parse_cve_page(url)
            if rows:
                save_results(rows)
            else:
                print(f"⚠️  Skipping save: No data extracted from {url}")
            time.sleep(0.5)  # politeness delay

    print("✅ Done.")

if __name__ == "__main__":
    main()
