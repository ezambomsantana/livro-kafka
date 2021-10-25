from flask import Flask
from consumer import Consumer
import json
import logging

# inicializa o consumidor
consumer = Consumer()
consumer.start()

app = Flask(__name__)

@app.route('/messages')
def get_messages():
    messages = consumer.get_messages()
    return json.dumps(messages)

@app.route('/last')
def get_last():
    messages = consumer.get_messages()
    if len(messages) > 0:
        return json.dumps(messages[-1])
    else:
        return 'lista vazia'

@app.route('/last_ten')
def get_last_ten():
    messages = consumer.get_messages()
    if len(messages) > 9:
        return json.dumps(messages[-10:])
    else:
        return json.dumps(messages[-len(messages):])

if __name__ == '__main__':
     app.run()