:- dynamic client/8.
:- dynamic node/3.
:- dynamic belongsTo/2.

trafficBottleneck(LineId, CongestionLevel) :-
    CallTime(Time),
    RoadTraffic(LineId, BeginTime, EndTime, Congestion),
    BeginTime =< Time,
    EndTime >= Time.

calculateCost(Ax, Ay, Bx, By, Value) :-
    node(Ax, Ay, _, LineId, _, _),
    node(Bx, By, _, LineId, _, _),
    trafficBottleneck(LineId, CongestionLevel).


/* direction and canMoveFromTo, to figure out if its allowed to move from one node to another */
direction(LineId, _, _) :-
    line(LineId, _, _, OneWay, _, _, _, no, no, no, no, no, _, _, _, no, _, _),
    OneWay = no.

direction(LineId, CounterA, CounterB) :-
    line(LineId, _, _, OneWay, _, _, _, no, no, no, no, no, _, _, _, no, _, _),
    OneWay = yes,
    CounterA < CounterB.

direction(LineId, CounterA, CounterB) :-
    line(LineId, _, _, OneWay, _, _, _, no, no, no, no, no, _, _, _, no, _, _),
    OneWay = -1,
    CounterA > CounterB.

canMoveFromTo(NodeIdA, NodeIdB) :-
    node(_, _, NodeIdA, LineId, CounterA),
    node(_, _, NodeIdB, LineId, CounterB),
    direction(LineId, CounterA, CounterB).


/* Client */
clientSpeaks(Lang) :-
    client(_, _, _, _, _, _, Lang, _).

clientPassengers(Number) :-
    client(_, _, _, _, _, Number, _, _).

clientLuggage(Number) :-
    client(_, _, _, _, _, _, _, _, Number).


/* Taxi Driver and Ranking */
driverAvailable(DriverId) :-
    taxi(_, _, DriverId, yes, _, _, _).

taxiCapacity(DriverId, Capacity) :-
    taxi(_, _, DriverId, _, Number, _, _).

taxiRating(DriverId, Rating) :-
    taxi(_, _, DriverId, _, _, Rating, _).

isDriverQualified(DriverId) :-
    clientSpeaks(Lang),
    taxiSpeaks(DriverId, Lang),
    driverAvailable(DriverId),
    clientPassengers(Number),
    taxiCapacity(DriverId, Capacity),
    Number <= Capacity,
    clientLuggage(Luggage),
    



