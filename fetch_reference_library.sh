#!/bin/sh
java -cp bin:libs/jsoup-1.7.2.jar:../webapp/war/WEB-INF/classes:../webapp/war/WEB-INF/lib/jdo2-api-2.3-eb.jar ws.holybook.documents.referencelibrary.CrawlReferenceLibrary $* 
