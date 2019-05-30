import csv


def writeDictionaryListToCSVfile(name, dict_list, column_names):
    try:
        with open(name, 'w', encoding='utf-8',newline='') as csvFile:
            writer = csv.DictWriter(csvFile, fieldnames=column_names)
            writer.writeheader()
            for data in dict_list:
                writer.writerow(data)

    except:
        print("I/O error!")

    finally:
        print("work done!")
