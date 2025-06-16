

import requests
from bs4 import BeautifulSoup
import csv
import re
import os
import time

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

def save_results(rows):
    file_exists = os.path.exists(OUTPUT_FILE)
    with open(OUTPUT_FILE, "a", newline="") as csvfile:
        writer = csv.DictWriter(csvfile, fieldnames=["url", "cve", "introducing_commit", "fix_commit", "repository"])
        if not file_exists:
            writer.writeheader()
        for row in rows:
            writer.writerow(row)
