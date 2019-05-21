from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import os
import pandas as pd
import re



"""
# seperate in columns
> categroy seperator

자연과학>물리학#자연과학>일반

"""


def getCategory(df, s_index, e_index):
    result = []
    for index in range(s_index, e_index):
        temp_dict = {}
        row = df.loc[index]
        temp_dict['no'] = row['no']
        temp_dict['category_kdc'] = 'null'
        temp_dict['category_yes24'] = 'null'
        temp_dict['keywords_yes24'] = 'null'
        result.append(temp_dict)

if __name__ == '__main__' :
    df = pd.read_csv('combined_2010-01-01_2019-03-01.csv', sep=',', encoding='utf-8')
    result_list = getCategory(df, 0, 1000)
