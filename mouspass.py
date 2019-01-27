import os
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
test_images = [[(0,0),(0,0),(1,2),(3,4),(6,7),(9,11),(0,0)],[(0,1),(0,0),(1,2),(3,5),(6,7),(10,11),(0,0)]]
test_images = np.asarray(test_images)
train_labels = np.array([1, 0])
test_labels = np.array([1, 1])
#train_labels = np.asarray(train_labels)
class_names = ['True','False']
#print (train_images)
#print (train_images.shape)
#print (len(train_labels))
#print (train_labels)
#class_names = ['T-shirt/top', 'Trouser', 'Pullover', 'Dress', 'Coat', 'Sandal', 'Shirt', 'Sneaker', 'Bag', 'Ankle boot']
#test
#train_images = train_images / 255.0

#test_images = test_images / 255.0




#Build the model


def create_model():
	model = keras.Sequential([
    	keras.layers.Flatten(input_shape=(7, 2)),
    	keras.layers.Dense(128, activation=tf.nn.relu),
    	keras.layers.Dense(2, activation=tf.nn.softmax)
	])

	model.compile(optimizer='adam',
        	      loss=tf.keras.losses.sparse_categorical_crossentropy,
        	      metrics=['accuracy'])

	return model 

#create checkpoints
checkpoint_path = "training_1/cp.ckpt"
checkpoint_dir = os.path.dirname(checkpoint_path)


# Create checkpoint callback
cp_callback = tf.keras.callbacks.ModelCheckpoint(checkpoint_path,
                                                 save_weights_only=True,
                                                 verbose=1, period=5)#saves every 5 epochs

#stores training data and gets it back
model = create_model()

model.fit(train_images, train_labels,  epochs = 50,
			validation_data = (test_images,test_labels),
          	callbacks = [cp_callback], verbose =0)  # pass callback to training



loss, acc = model.evaluate(test_images, test_labels)
print("Untrained model, accuracy: {:5.2f}%".format(100*acc))


model.load_weights(checkpoint_path)
loss,acc = model.evaluate(test_images, test_labels)
print("Restored model, accuracy: {:5.2f}%".format(100*acc))


#test that shit
# include the epoch in the file name. (uses `str.format`)
checkpoint_path = "training_2/cp-{epoch:04d}.ckpt"
checkpoint_dir = os.path.dirname(checkpoint_path)

cp_callback = tf.keras.callbacks.ModelCheckpoint(
    checkpoint_path, verbose=1, save_weights_only=True,
    # Save weights, every 5-epochs.
    period=5)

model = create_model()
model.save_weights(checkpoint_path.format(epoch=0))
model.fit(train_images, train_labels,
          epochs = 50, callbacks = [cp_callback],
          validation_data = (test_images,test_labels),
          verbose=0)

latest = tf.train.latest_checkpoint(checkpoint_dir)
#tests training data
"""
test_loss, test_acc = model.evaluate(test_images, test_labels)

print('Test accuracy:', test_acc)


img = test_images[1]
img = (np.expand_dims(img, 0))
predictions_single = model.predict(img)

print(predictions_single)
x = np.argmax(predictions_single[0])
print(x)
"""
