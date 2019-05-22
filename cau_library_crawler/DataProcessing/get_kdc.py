"""
나무위키에 저장되어 있는 KDC 6판 데이터를 불러와서
JSON 파일로 저장하기 위한 코드입니다.

   번호      카테고리

  '0'04     컴퓨터과학
"""
from bs4 import BeautifulSoup
import cau_library_crawler.library_crawler.writeCSV as wc
import requests
import re


url = "https://namu.wiki/w/%ED%95%9C%EA%B5%AD%EC%8B%AD%EC%A7%84%EB%B6%84%EB%A5%98%EB%B2%95%20%EC%9A%94%EB%AA%A9%ED%91%9C"


html_source = requests.get(url).text
soup = BeautifulSoup(html_source, "html.parser")
list_div = soup.find_all("div" , {"class": "wiki-heading-content"})
result_list = []
for index in range(1, len(list_div)):
    div = list_div[index]
    list_div_div = div.find_all("div", {"class" : "wiki-paragraph"})
    for div_div in list_div_div :
        string = div_div.getText()
        if len(string) < 3:
            continue
        numbering = re.search(r'\d\d\d', string)
        category = string[4:].replace(',', '')
        if len(category) < 1:
            category = "null"
        result_list.append({'no': str(numbering.group(0)) , 'category' : category})


wc.writeDictionaryListToCSVfile("kdc.csv", result_list, ["no", "category"])
