mkdir -p classes
javac -d classes $(find src -type f) && java -cp classes sortieren.schnell.Main
