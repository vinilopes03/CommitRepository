

# Commit 2: Implement utility function for generating month range
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


# Commit 4: Implement function to parse CVE page
def parse_cve_page(url):
    try:
        res = requests.get(url, timeout=20)
        res.raise_for_status()
        soup = BeautifulSoup(res.text, "html.parser")
        raw_text = soup.get_text()

        # ✅ Find CVE ID anywhere in body, not just title
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


# Commit 6: Implement main function to orchestrate data extraction
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
