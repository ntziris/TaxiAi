:- dynamic client/8.
:- dynamic node/3.
:- dynamic belongsTo/2.

/* ************************************************************* */



isNight(Time) :-
    Time >= 2000,
    Time =< 2359.

isNight(Time) :-
    Time >= 0000,
    Time <= 0600.

hasLights(LineId, Answer) :-
    lines(LineId, _, _, _, Answer, _, _, _, _, _, _, _, _, _, _, _, _, _).

lightSatisfiability(LineId, lightsLevel) :-
    CallTime(Time),
    isNight(Time),
    hasLights(LineId, yes),
    lightsLevel = 0.8.

lightSatisfiability(_, 1).

findCongestionLevel(high, 1).
findCongestionLevel(medium, 0.8).
findCongestionLevel(low, 0.6).

trafficBottleneck(LineId, CongestionLevel) :-
    CallTime(Time),
    RoadTraffic(LineId, BeginTime, EndTime, Congestion),
    BeginTime =< Time,
    EndTime >= Time,
    findCongestionLevel(Congestion, CongestionLevel).

calculateCost(NodeIdA, NodeIdB, Value) :-
    node(_, _, NodeIdA, LineId, _, _),
    node(_, _, NodeIdB, LineId, _, _),
    trafficBottleneck(LineId, CongestionLevel),
    lightSatisfiability(LineId, lightsLevel),
    Value is CongestionLevel * lightsLevel.






/* ************************************************************* */

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
    Number <= Capacity.





