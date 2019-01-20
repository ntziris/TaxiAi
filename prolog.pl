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

canMoveFromTo(NodeIdA, NodeIdB) :-
    node(_, _, NodeIdA, LineId, CounterA),
    node(_, _, NodeIdB, LineId, CounterB),
    direction(LineId, CounterA, CounterB).


/* Client */
clientSpeaks(Lang) :-
    client(_, _, _, _, _, _, Lang, _).


/* Taxi Driver Ranks */

driverLan(Lang) :-


isDriverQualified(DriverId) :-
    clientSpeaks(Lang),
