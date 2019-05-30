import time
import calendar
from cau_library_crawler.library_crawler.writeCSV import writeDictionaryListToCSVfile
from cau_library_crawler.library_crawler.DATA import *
from cau_library_crawler.library_crawler.crawler import Crawler


def main(START_DATE, END_DATE):
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
    myCrawler.close()
    csv_columns = ['title', 'writer', 'publication', 'callno', 'href']

    #책 목록 csv 파일로 저장하기
    filename = "../data/BOOKLIST_"+START_DATE+"_"+END_DATE+".csv"
    writeDictionaryListToCSVfile(filename,booklist, csv_columns)

main("2019-01-01","2019-03-01")
'''
if __name__ == '__main__':
    for year in range(2010, 2019):
        for i in range(0,4):
            s_month = 1 + i*3
            e_month = 3 + i*3
            e_day = calendar.monthrange(year,e_month)[1]
            START_DATE = str(year)+"-"+f"{s_month:02}-"+"01"
            END_DATE = str(year) + "-" + f"{e_month:02}-" + f"{e_day}"
            print(END_DATE)
            main(START_DATE, END_DATE)
'''

