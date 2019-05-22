from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import os
import pandas as pd
import re
import cau_library_crawler.library_crawler.writeCSV as wc



"""
# seperate in columns
> categroy seperator

자연과학>물리학#자연과학>일반

"""


def getCategory(df,kdc_df, s_index, e_index):
    result = []
    kdc_dict = {}
    for index in range(0,kdc_df.shape[0]):
        kdc_dict[str(kdc_df['no'][index])] = kdc_df['category'][index]

    for index in range(s_index, e_index):
        temp_dict = {}
        row = df.loc[index]
        temp_dict['no'] = row['no']
        kdc_book = str(row['callno'])
        if kdc_book[:3] == 'nan':
            temp_dict['category_kdc'] = 'null'
        else:
            key_kdc = re.search(r'\d\d\d', kdc_book).group(0)
            if key_kdc in kdc_dict.keys():
                temp_dict['category_kdc'] = kdc_dict[key_kdc]
            else:
                print(key_kdc)
        temp_dict['category_yes24'] = 'null'
        temp_dict['keywords_yes24'] = 'null'
        result.append(temp_dict)

    return result

if __name__ == '__main__' :
    df = pd.read_csv('combined_2010-01-01_2019-03-01.csv', dtype={'callno':str},sep=',', encoding='utf-8')
    kdc_df = pd.read_csv('kdc.csv', dtype={'no':str}, sep=',', encoding='utf-8')
    result_list = getCategory(df, kdc_df, 0, df.shape[0])
    wc.writeDictionaryListToCSVfile("no_category.csv", result_list,['no','category_kdc', 'category_yes24', 'keywords_yes24'])
