Python 3.12.1 (tags/v3.12.1:2305ca5, Dec  7 2023, 22:03:25) [MSC v.1937 64 bit (AMD64)] on win32
Type "help", "copyright", "credits" or "license()" for more information.
>>> from flask import Flask, jsonify, request
... from flask_pymongo import PyMongo
... from bson import ObjectId
... 
... app = Flask(__name__)
... app.config['MONGO_URI'] = 'mongodb://localhost:27017/rental-mobil'
... mongo = PyMongo(app)
... 
... @app.route('/api/cars', methods=['GET'])
... def get_available_cars():
...     cars = list(mongo.db.cars.find({'available': True}, {'_id': 0}))
...     return jsonify(cars)
... 
... @app.route('/api/rent/<car_id>', methods=['POST'])
... def rent_car(car_id):
...     try:
...         car = mongo.db.cars.find_one_and_update(
...             {'_id': ObjectId(car_id), 'available': True},
...             {'$set': {'available': False}},
...             return_document=True
...         )
... 
...         if car:
...             return jsonify({'message': f'Successfully rented {car["brand"]} {car["name"]}'}), 200
...         else:
...             return jsonify({'error': 'Car not found or already rented'}), 404
...     except Exception as e:
...         print(e)
...         return jsonify({'error': 'Internal Server Error'}), 500
... 
if __name__ == '__main__':
    app.run(debug=True)
