
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

