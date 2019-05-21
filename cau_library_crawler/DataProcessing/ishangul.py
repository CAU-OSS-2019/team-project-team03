# from https://github.com/chandong83/python_hangul_check_function/blob/master/han_check.py
import re

#if string contains one hangul character then return true else false
def isHangul(text :str) -> bool :
    return True if len(re.findall(u'[\u3130-\u318F\uAC00-\uD7A3]+', text)) > 0 else False


