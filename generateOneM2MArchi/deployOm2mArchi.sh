#!/bin/bash

# Creation of the Floor:
i=0;

for floor in Floor{1,2}_Manager ; do

    
    i=$((i+1));
	echo floor manager
    curl -X POST 'http://127.0.0.1:8080/~/in-cse' -H 'cache-control: no-cache' -H 'content-type: application/xml;ty=2' -H 'x-m2m-origin: admin:admin' \
		-d "<m2m:ae xmlns:m2m=\"http://www.onem2m.org/xml/protocols\" rn=\"$floor\">
		     <api>FLOOR</api>
	                  <lbl>Type/floor Category/floor location/$floor</lbl>  
		       <rr>false</rr>
		    </m2m:ae>"
	echo ""

    # Creation de la Room
    for room in ROOM{${i}00,${i}01} ; do
		echo room : http://127.0.0.1:8080/~/in-cse/in-name/$floor : $room
        curl -X POST "http://127.0.0.1:8080/~/in-cse/in-name/$floor" -H 'cache-control: no-cache' -H 'content-type: application/xml;ty=3' -H 'x-m2m-origin: admin:admin' \
				-d "<m2m:cnt xmlns:m2m=\"http://www.onem2m.org/xml/protocols\" rn=\"$room\">
					</m2m:cnt>"
	    echo ""

        # Creation of the Sensors :
		for sensor in Outside_Temp Inside_Temp ; do
			curl -X POST "http://127.0.0.1:8080/~/in-cse/in-name/$floor/$room" -H 'cache-control: no-cache' -H 'content-type: application/xml;ty=3' -H 'x-m2m-origin: admin:admin' \
				-d "<m2m:cnt xmlns:m2m=\"http://www.onem2m.org/xml/protocols\" rn=\"$sensor\">
					</m2m:cnt>"
			echo ""

			case "$sensor" in
				Outside_Temp)
					category="temperature"
					data="20"
					unit="celsius"
					;;
				Inside_Temp)
					category="temperature"
					data="20"
					unit="celsius"
					;;
			esac
					#Add content instance data to sensor container
					curl -X POST "http://127.0.0.1:8080/~/in-cse/in-name/$floor/$room/$sensor" -H 'cache-control: no-cache' -H 'content-type: application/xml;ty=4' -H 'x-m2m-origin: admin:admin' \
				-d "<m2m:cin xmlns:m2m=\"http://www.onem2m.org/xml/protocols\">
					<rn>DATA</rn>
					<cnf>message</cnf>
<con>
	&lt;obj&gt;
		&lt;str name=&quot;location&quot; val=&quot;$floor/$room&quot;/&gt;
		&lt;str name=&quot;category&quot; val=&quot;$category&quot;/&gt;
		&lt;float name=&quot;data&quot; val=&quot;$data&quot;/&gt;
		&lt;str name=&quot;unit&quot; val=&quot;$unit&quot;/&gt;
	&lt;/obj&gt;
</con>
					</m2m:cin>"
		done

		# Creation of the Actuators :
		for actuator in Alarm Window Door Presence; do
			curl -X POST "http://127.0.0.1:8080/~/in-cse/in-name/$floor/$room" -H 'cache-control: no-cache' -H 'content-type: application/xml;ty=3' -H 'x-m2m-origin: admin:admin' \
				-d "<m2m:cnt xmlns:m2m=\"http://www.onem2m.org/xml/protocols\" rn=\"$actuator\">
					</m2m:cnt>"
			echo ""

			case "$actuator" in
				Alarm)
					category="alarm"
					state="ON"
					;;
				Window)
					category="window"
					state="OPEN"
					;;
				Door)
					category="door"
					state="OPEN"
					;;
				Presence)
					category="presence"
					state="FALSE"
			esac
					#Add content instance status to actuator container
					curl -X POST "http://127.0.0.1:8080/~/in-cse/in-name/$floor/$room/$actuator" -H 'cache-control: no-cache' -H 'content-type: application/xml;ty=4' -H 'x-m2m-origin: admin:admin' \
				-d "<m2m:cin xmlns:m2m=\"http://www.onem2m.org/xml/protocols\">
					<rn>DATA</rn>
					<cnf>message</cnf>
<con>
	&lt;obj&gt;
		&lt;str name=&quot;location&quot; val=&quot;$floor/$room&quot;/&gt;
		&lt;str name=&quot;category&quot; val=&quot;$category&quot;/&gt;
		&lt;str name=&quot;state&quot; val=&quot;$state&quot;/&gt;
	&lt;/obj&gt;
</con>
					</m2m:cin>"
		done
	done
done