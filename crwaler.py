from selenium import webdriver
import csv

try:
    driver = webdriver.Chrome('./driver/chromedriver.exe')
except:
    driver = webdriver.Chrome('./driver/chromedriver')

f = open('BookList_IT.csv', 'w', newline='\n')
wr = csv.writer(f)

rank = 1
bookId = 1

for y in range(1,7):
    page_path = "http://www.yes24.com/24/category/bestseller?CategoryNumber=001001003031&sumgb=03&PageNumber=" + str(y)
    driver.get(page_path)

    for x in range(1, 21):
        title_xpath = '//*[@id="category_layout"]/tbody/tr[' + str(2*x-1) + ']/td[3]/p[1]/a[1]'
        title = driver.find_element_by_xpath(title_xpath).text

        if title.__contains__("[") | title.__contains__("]"):
            continue

        author_xpath = '//*[@id="category_layout"]/tbody/tr[' + str(2*x-1) + ']/td[3]/div/a[1]'
        author = driver.find_element_by_xpath(author_xpath).text

        str_xpath = '//*[@id="category_layout"]/tbody/tr[' + str(2*x-1) + ']/td[3]/div'
        string = driver.find_element_by_xpath(str_xpath).text

        temp = string.split(" | ")
        publisher = temp[1]
        date = temp[2]

        book = []

        book.append(bookId)
        book.append(title)
        book.append(author)
        book.append(publisher)
        book.append("IT")
        book.append(date)
        book.append(rank)
        print(book)
        wr.writerow(book)

        bookId += 1
        rank += 1