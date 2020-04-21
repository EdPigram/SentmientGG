import json 
from datetime import date, time 

def serialize(obj):

    if isinstance(obj, date):
        serial = obj.isoformat()
        return serial

    if isinstance(obj, time):
        serial = obj.isoformat()
        return serial

    return obj.__dict__

def serializeAsJson(obj): 
    return json.dumps(obj, default=serialize)

def serializeNormal(obj): 
    return json.dumps(obj)

class SerializableGenerator(list):

    def __init__(self, iterable):
        tmp_body = iter(iterable)
        try:
            self._head = iter([next(tmp_body)])
            self.append(tmp_body)
        except StopIteration:
            self._head = []

    def __iter__(self):
        return itertools.chain(self._head, *self[:1])

