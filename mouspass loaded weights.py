import csv
### IMPORT FROM CSV
with open('example.csv') as csvfile:
    readCSV = csv.reader(csvfile, delimiter=',')
    list = []
    for row in readCSV:
        list.append((row[0], row[1]))

    print(list)