import csv


def writeDictionaryListToCSVfile(name : str , dict_list : dict, column_names :list, writeheader=True):
    try:
        with open(name, 'w', encoding='utf-8',newline='') as csvFile:
            writer = csv.DictWriter(csvFile, fieldnames=column_names)
            if writeheader:
                writer.writeheader()
            for data in dict_list:
                writer.writerow(data)
    except:
        print("I/O error!")

    finally:
        print("work done!")
