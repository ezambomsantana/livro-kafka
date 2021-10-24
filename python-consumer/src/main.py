from flask import Flask
from consumer import Consumer
import json
import logging

# inicializa o consumidor
consumer = Consumer()
consumer.start()

app = Flask(__name__)

@app.route('/')
def get_messages():
    messages = consumer.get_messages()
    return json.dumps(messages)

@app.route('/last')
def get_messages():
    messages = consumer.get_messages()
    return json.dumps(messages)

@app.route('/last_ten')
def get_messages():
    messages = consumer.get_messages()
    return json.dumps(messages)

@app.route('/stop_consumer')
def stop_consumer():
    consumer.stop()
    return 'consumer stopped'

if __name__ == '__main__':
     app.run()