

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


def save_results(rows):
    file_exists = os.path.exists(OUTPUT_FILE)
    with open(OUTPUT_FILE, "a", newline="") as csvfile:
        writer = csv.DictWriter(csvfile, fieldnames=["url", "cve", "introducing_commit", "fix_commit", "repository"])
        if not file_exists:
            writer.writeheader()
        for row in rows:
            writer.writerow(row)

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
