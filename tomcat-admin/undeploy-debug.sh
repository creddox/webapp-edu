#!/bin/bash

ROOT_UID=0
#Location of webapps directory
DIR_WEBAPP='/var/lib/tomcat7/webapps'

if [ $(id -u) -ne $ROOT_UID ]
then
  echo 'You are not root.'
  exit
fi

if [ -z $1 ]
then
  echo 'This script will only work if you supply the name of the web application        to undeploy.'
  exit
else
  echo 'Stopping Tomcat.'
  /etc/init.d/tomcat7.debug stop

  echo "Removing $1.war."
  rm -f $DIR_WEBAPP/$1.war

  echo "Removing directory $1."
  rm -rf $DIR_WEBAPP/$1

  echo 'Starting Tomcat in Debug-Mode.'
  /etc/init.d/tomcat7.debug debug
fi
