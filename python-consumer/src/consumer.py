import threading
import json
import logging
from kafka import KafkaConsumer

class Consumer(threading.Thread):
            
    def __init__(self):
        threading.Thread.__init__(self)
        self.stop_event = threading.Event()

    def run(self):
        try:
            consumer = KafkaConsumer(
                bootstrap_servers=['kafka:9092'],
                group_id="grupo_python",                         
                consumer_timeout_ms=30000
            )
            consumer.subscribe(['SHOP_TOPIC_EVENT'])

            while not self.stop_event.is_set():
                for message in consumer:
                    logging.info(
                        'Receiving message from topic SHOP_TOPIC_EVENT')
                    self.messages.append(
                        json.loads(message.value.decode("utf-8")))
                    

            consumer.close()      
        except IOError as exception:
            print("Read error")
            logging.error(exception)

    def get_messages(self):
        return self.messages

    messages = []