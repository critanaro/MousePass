# TensorFlow and tf.keras
import tensorflow as tf
from tensorflow import keras

# Helper libraries
import numpy as np
import matplotlib.pyplot as plt

print(tf.__version__)

#fashion_mnist = keras.datasets.fashion_mnist
#(train_images, train_labels), (test_images, test_labels) = fashion_mnist.load_data()


#Import the DataSet

train_images = [[(0,0),(0,0),(1,2),(3,4),(6,7),(9,11),(0,0)],[(0,0),(6,5),(3,4),(7,6),(10,4),(9,13),(0,0)]]
train_images = np.asarray(train_images)
train_labels = [1,0]
train_labels = np.asarray(train_labels)
class_names = ['True','False']

#class_names = ['T-shirt/top', 'Trouser', 'Pullover', 'Dress', 'Coat', 'Sandal', 'Shirt', 'Sneaker', 'Bag', 'Ankle boot']
#test
#train_images = train_images / 255.0

#test_images = test_images / 255.0


#Build the model


model = keras.Sequential([
    #keras.layers.Flatten(input_shape=(28, 28)), - not needed as this takes a 2d array for each pic and transforms it into a 1d array.
    keras.layers.Dense(128, activation=tf.nn.relu),
    keras.layers.Dense(2, activation=tf.nn.softmax)
])

model.compile(optimizer='adam',
              loss='sparse_categorical_crossentropy',
              metrics=['accuracy'])

model.fit(train_images, train_labels, epochs=5)

test_loss, test_acc = model.evaluate(test_images, test_labels)

print('Test accuracy:', test_acc)


img = test_images[0]
img = (np.expand_dims(img, 0))
predictions_single = model.predict(img)

print(predictions_single)
x = np.argmax(predictions_single[0])
print(x)
