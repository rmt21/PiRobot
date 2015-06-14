import time
import initio

initio.init()

dist = initio.getDistance()
time.sleep(1)
initio.cleanup()
print int(dist)
