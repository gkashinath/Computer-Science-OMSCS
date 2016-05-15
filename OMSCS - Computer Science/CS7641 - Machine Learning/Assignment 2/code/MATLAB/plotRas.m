
function [] = plotRas (~)

if ( nargin == 0 )
    [X,Y] = meshgrid ( -5:0.05:5, -5:0.05:5);
    Z = Ras(X,Y);

    surfc (X,Y,Z,'EdgeColor','none','LineStyle','none');
    figure;
end;

[X,Y] = meshgrid ( -5:0.05:5, -5:0.05:5);
Z = Ras(X,Y);

contour(X,Y,Z,1:4:40,'ShowText','on');