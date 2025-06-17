

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
