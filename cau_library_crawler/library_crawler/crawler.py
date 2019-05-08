import time
from library_crawler.DATA import *
from selenium import webdriver
from selenium.webdriver.common.keys import Keys




class Crawler:
    def __init__(self):
        self.driver = webdriver.Chrome("../chromedriver_win32/chromedriver.exe")
        self.driver.implicitly_wait(DELAY)
    def get_url(self, url) :
        self.driver.get(url)

    def get_element_by_xpath(self, xpath):
        return self.driver.find_element_by_xpath(xpath)

    def click(self, element):
        element.click()

    def setDate(self, start, end, start_xpath, end_xpath):
        startdate_input = self.driver.find_element_by_xpath(start_xpath)
        startdate_input.clear()
        startdate_input.send_keys(start)
        startdate_input.send_keys(Keys.TAB)
        enddate_input = self.driver.find_element_by_xpath(end_xpath)
        enddate_input.clear()
        enddate_input.send_keys(end)

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
            comp = total - LENGTH*(i)
            times2 = LENGTH if comp > LENGTH else comp
            print(i+1)
            for i in range(1, times2 + 1):
                try :
                    title = self.driver.find_element_by_xpath(
                        "/html/body/div[1]/div[3]/div[2]/div[2]/div[2]/div[2]/div[1]/div/div[2]/div[4]/div[" + str(
                            i) + "]/div[3]/ul/li[1]/a[1]/span").text
                except:
                    print("No title book was found! continue")
                    continue
                try :
                    writer = self.driver.find_element_by_xpath(
                        "/html/body/div[1]/div[3]/div[2]/div[2]/div[2]/div[2]/div[1]/div/div[2]/div[4]/div[" + str(
                            i) + "]/div[3]/ul/li[2]/span").text
                except:
                    writer = "null"
                try:
                    publication = self.driver.find_element_by_xpath(
                        "/html/body/div[1]/div[3]/div[2]/div[2]/div[2]/div[2]/div[1]/div/div[2]/div[4]/div[" + str(
                            i) + "]/div[3]/ul/li[3]/span").text
                except:
                    publication = "null"
                try :
                    callno = self.driver.find_element_by_xpath(
                        "/html/body/div[1]/div[3]/div[2]/div[2]/div[2]/div[2]/div[1]/div/div[2]/div[4]/div[" + str(
                            i) + "]/div[3]/ul/li[5]/ul/li/span[2]").text
                except:
                    callno = "null"
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
            time.sleep(DELAY_PER_NEXT)
        return booklist

