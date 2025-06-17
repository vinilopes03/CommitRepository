

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
POLITENESS_DELAY = 0.5

# Add some noise here
NOISE_CONSTANT = "This is some random noise added to the code."
