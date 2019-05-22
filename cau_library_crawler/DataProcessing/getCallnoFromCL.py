import requests
from selenium import webdriver
from cau_library_crawler.library_crawler.DATA import *
import time

driver = webdriver.Chrome("../chromedriver_win32/chromedriver.exe")
driver.implicitly_wait(DELAY)
driver.get('https://library.cau.ac.kr/#/search/detail/4822474')
time.sleep(5)
print(driver.page_source)