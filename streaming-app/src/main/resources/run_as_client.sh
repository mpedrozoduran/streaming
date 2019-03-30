#!/bin/sh
DIR=`pwd`
java -jar streaming-app-1.0-SNAPSHOT-jar-with-dependencies.jar -channelFile "$DIR/channels.txt" -mode client -port 8888 -resourceToStream /Users/mpedrozoduran/Downloads/The_Ninth_Gate_1999/The.Ninth.Gate.1999.720p.BluRay.x264.YIFY.mp4 &
