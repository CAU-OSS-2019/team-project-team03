import time
from library_crawler.writeCSV import writeDictionaryListToCSVfile
from library_crawler.DATA import *
from library_crawler.crawler import Crawler

START_DATE = "2018-07-01"
END_DATE = "2018-12-31"

if __name__ == '__main__':
    myCrawler = Crawler()
    myCrawler.get_url("https://library.cau.ac.kr/#/new/si")
    time.sleep(5)
    myCrawler.setDate(START_DATE, END_DATE, START_XPATH, END_XPATH)
    time.sleep(3)

    #검색 버튼 누르기
    myCrawler.click(myCrawler.get_element_by_xpath(SEARCH_BTN_XPATH))
    time.sleep(DELAY_PER_NEXT)
    # 검색항목을 서울학술원으로 한정
    myCrawler.click(myCrawler.get_element_by_xpath(OPTION_SEOUL_XPATH))
    time.sleep(DELAY_PER_NEXT)
    # 검색항목을 단행본으로 한정
    myCrawler.click(myCrawler.get_element_by_xpath(OPTION_ONEBOOK_XPATH))
    time.sleep(DELAY_PER_NEXT)

    #책 목록 뽑아오기
    booklist = myCrawler.getBookData()
    csv_columns = ['title', 'writer', 'publication', 'callno', 'href']

    #책 목록 csv 파일로 저장하기
    filename = "../data/BOOKLIST_"+START_DATE+"_"+END_DATE+".csv"
    writeDictionaryListToCSVfile(filename,booklist, csv_columns)


