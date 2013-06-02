#!/bin/sh
java -cp bin:libs/jsoup-1.7.2.jar:../data-model/bin ws.holybook.documents.referencelibrary.CrawlReferenceLibrary $1 
