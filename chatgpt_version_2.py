

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


def get_repository(text):
    match = re.search(r"https://git\.kernel\.org[^ \n\"]+", text)
    return match.group(0) if match else ""


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
