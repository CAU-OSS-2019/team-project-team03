import time
from cau_library_crawler.library_crawler.DATA import *
from selenium import webdriver
from selenium.webdriver.common.keys import Keys




class Crawler:
    def __init__(self):
        #셀레니움 크롬 브라우저 실행
        self.driver = webdriver.Chrome("../chromedriver_win32/chromedriver.exe")
        self.driver.implicitly_wait(DELAY)
    # driver을 url 로 이동시키는 함수
    def get_url(self, url) :
        self.driver.get(url)

    # xpath를 통해 element를 얻어 오는 함수
    def get_element_by_xpath(self, xpath):
        return self.driver.find_element_by_xpath(xpath)
    # click 하는 함수 굳이 필요 있나 싶긴 함
    def click(self, element):
        element.click()

    # 데이터를 뽑아올 시작일과 종료일을 입력하고 실제 dirver의 검색 옵션을 바꿔주는 함수
    def setDate(self, start, end, start_xpath, end_xpath):
        startdate_input = self.driver.find_element_by_xpath(start_xpath)
        startdate_input.clear()
        startdate_input.send_keys(start)
        startdate_input.send_keys(Keys.TAB)
        enddate_input = self.driver.find_element_by_xpath(end_xpath)
        enddate_input.clear()
        enddate_input.send_keys(end)

    # 옵션을 바꿔서 얻어진 결과물을 뽑아와서 리스트 형태로 반환
    def getBookData(self):
        total_Count = self.driver.find_element_by_xpath(TOTAL_COUNT_XPATH).text
        total = int(total_Count.split('/')[1].replace("건", "").replace(',','').strip())
        print("총 권수 : " + str(total))
        times = int(total / LENGTH)
        times += 1 if total % LENGTH > 0 else 0
        try :
            next_btn = self.driver.find_element_by_xpath(NEXT_BTN_XPATH)
        except:
            next_btn = None
        booklist = []
        for i in range(0, times):
            # 결과가 35개고 길이가 20개일 때 마지막은 15번 실행되어야함
            comp = total - LENGTH*(i)
            times2 = LENGTH if comp > LENGTH else comp
            # 필요는 없는데 지금 진행 상황을 출력하기 위함
            print(i+1)
            for i in range(1, times2 + 1):
                # 책 제목 가져오기
                try :
                    title = self.driver.find_element_by_xpath(
                        "/html/body/div[1]/div[3]/div[2]/div[2]/div[2]/div[2]/div[1]/div/div[2]/div[4]/div[" + str(
                            i) + "]/div[3]/ul/li[1]/a[1]/span").text
                except:
                    # 제목이 없을 경우 생략
                    print("No title book was found! continue")
                    continue
                #책 저자 가져오기
                try :
                    writer = self.driver.find_element_by_xpath(
                        "/html/body/div[1]/div[3]/div[2]/div[2]/div[2]/div[2]/div[1]/div/div[2]/div[4]/div[" + str(
                            i) + "]/div[3]/ul/li[2]/span").text
                except:
                    #없으면 "null"로 저장
                    writer = "null"
                # 책 발행 정보 가져오기 , 출판사와 출판년도를 분리하는 작업이 필요해 보임
                try:
                    publication = self.driver.find_element_by_xpath(
                        "/html/body/div[1]/div[3]/div[2]/div[2]/div[2]/div[2]/div[1]/div/div[2]/div[4]/div[" + str(
                            i) + "]/div[3]/ul/li[3]/span").text
                except:
                    publication = "null"
                #책 분류 번호, 이 것을 사용해서 책의 카테고리를 분류할 예정
                try :
                    callno = self.driver.find_element_by_xpath(
                        "/html/body/div[1]/div[3]/div[2]/div[2]/div[2]/div[2]/div[1]/div/div[2]/div[4]/div[" + str(
                            i) + "]/div[3]/ul/li[5]/ul/li/span[2]").text
                except:
                    callno = "null"
                # 책의 상세내용에 대한 주소값, 끝에 있는 숫자가 고유넘버로 사용 가능
                try :
                    href = self.driver.find_element_by_xpath(
                        "/html/body/div[1]/div[3]/div[2]/div[2]/div[2]/div[2]/div[1]/div/div[2]/div[4]/div[" + str(
                            i) + "]/div[3]/ul/li[1]/a[1]").get_attribute("href")
                except:
                    href = "null"

                book = {'title':title,
                        'writer':writer,
                        'publication':publication,
                        'callno':callno,
                        'href':href}
                booklist.append(book)
            try :
                next_btn.click()
            except:
                print("no more")
                break
            #현재는 3초 쉬게 만들어놨는데 딱히 이상 없었음, 더 줄이면 좋겠지만 안정성을 위해서 길게 잡음
            time.sleep(DELAY_PER_NEXT)
        return booklist

