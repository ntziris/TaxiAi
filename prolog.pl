:- dynamic client/8.
:- dynamic node/3.
:- dynamic belongsTo/2.

calculateCost(Ax, Ay, Bx, By, Value) :-
    node(Ax, Ay, _, LineId, _, _),
    node(Bx, By, _, LineId, _, _),
    trafficBottleneck(LineId, CongestionLevel),
    Value is CongestionLevel.



