import csv
import tensorflow as tf
import numpy as np
from tensorflow import keras
lengthofoneq = 4#10,000
lengthtotal = 20#50,000
### IMPORT FROM CSV
with open('example.csv') as csvfile:
    readCSV = csv.reader(csvfile, delimiter=',')
    masterlist = []
    list = []
    counter = 0
    for row in readCSV:
        if counter == lengthofoneq:
            masterlist.append(list)
            list = []
        if counter == 2 * lengthofoneq:
            masterlist.append(list)
            list = []
        if counter == 3 * lengthofoneq:
            masterlist.append(list)
            list = []
        if counter == 4 * lengthofoneq:
            masterlist.append(list)
            list = []
        if counter == 5 * lengthofoneq:
            masterlist.append(list)
            list = []
        list.append((row[0], row[1]))
        counter += 1



print(masterlist)
saved_model_path = "cp.ckpt.data-00000-of-00001"
model = keras.Sequential([
    keras.layers.Flatten(input_shape=(lengthofoneq, 2)),
    keras.layers.Dense(128, activation=tf.nn.relu),
    keras.layers.Dense(2, activation=tf.nn.softmax)
])

model.compile(optimizer='adam',
               loss=tf.keras.losses.sparse_categorical_crossentropy,
               metrics=['accuracy'])

f = open('printfile.txt', 'w')
counter2 = 0
for i in range(0,4):
    masterlistq = masterlist[i]
    img = (np.expand_dims(masterlistq, 0))
    predictions_single = model.predict(img)
    print(predictions_single)
    x = np.argmax(predictions_single[0])
    print(x)
    counter2 += x
if counter2 >= 4:
    f.write("True")
else:
    f.write("False")
f.close()



