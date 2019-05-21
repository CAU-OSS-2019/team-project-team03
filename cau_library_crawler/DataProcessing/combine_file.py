# title,writer,publication,callno,href
# -> no ||  title  || writer  || publication  || category || callno

import cau_library_crawler.DataProcessing.ishangul as ih
import cau_library_crawler.library_crawler.writeCSV as wc
import os
import pandas as pd
import re
import math

folder_path = "../data/"
files = os.listdir(folder_path)
combined_booklist = []

#모든 파일의 책 리스트를 하나로 통합합니다.
for file in files:
    count = 0
    with open(folder_path + file, 'r', encoding='utf-8') as csv_file:
        df = pd.read_csv(folder_path+file,sep=',',encoding='utf-8')
        length = df.shape[0]
        for index in range(length):
            if ih.isHangul(df.loc[index]['title']):
                count += 1
                temp_dict = {}
                row = df.loc[index]
                temp_dict['callno'] = row['callno']
                temp_dict['title'] = row['title']
                publication = row['publication']
                year = ''
                pub_company = ''
                if str(publication) == 'nan':
                    year = 'null'
                    pub_company = 'null'
                else :
                    try :
                        year = re.search(r'\d\d\d\d',publication).group(0)
                        pub_company = publication.replace(year, '').replace('서울 :', '').replace(',', '')
                    except :
                        year = 'null'
                        pub_company = publication
                temp_dict['pub_year'] = year
                temp_dict['pub_company'] = pub_company

                temp_dict['writer'] = row['writer']
                temp_dict['no'] = row['href'].split('/')[-1]
                combined_booklist.append(temp_dict)

def take_no(elem:dict) -> int:
    return int(elem['no'])
combined_booklist.sort(key =take_no , reverse=False)
columns = ['callno', 'title', 'pub_year', 'pub_company', 'writer', 'no']
wc.writeDictionaryListToCSVfile("combined_2010-01-01_2019-03-01.csv", combined_booklist, columns)