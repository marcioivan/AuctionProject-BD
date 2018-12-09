BIN=java/bin
SRC=java/src
LIB=java/libs

all: exeTest

# compileSellAPI: ${SRC}/api/SellAPI.java
# 	javac -d ${BIN} -classpath ${BIN} -extdirs ${BIN} -sourcepath ${SRC}/api ${SRC}/api/SellAPI.java

compileTest: ${SRC}/TestClient.java
	javac -d ${BIN} -classpath ${BIN}:${LIB}/* -sourcepath ${SRC} ${SRC}/TestClient.java

exeTest: compileTest
	java -classpath ${BIN}:${LIB}/* TestClient

clean:
	rm -rf ${BIN}/*
