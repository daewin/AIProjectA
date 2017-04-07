# AIProjectA
Artificial Intelligence Project A

COMP30024: Project Part A 

Pseudocode
function Count-Legal-Moves(type) returns the number of legal moves for particular type
	inputs: type, either Horizontal or Vertical
	for each piece with matching type in board do
		if Can-Move-Up(piece) then add count
		if Can-Move-Down(piece) then add count
		if Can-Move-Left(piece) then add count
if Can-Move-Right(piece) then add count
	end
	return count


function Can-Move-Up(piece) returns if we can move up
	inputs: piece, the piece we want to move
	position-Above ← Get-Position-Above(piece)
	if piece is Horizontal and position-Above is not past the boundary or is empty 					
    then return true 

	if piece is Vertical and position-Above is past the boundary or empty 
		then return true



function Can-Move-Down(piece) returns if we can move down
	position-Below ← Get-Position-Below (piece)
	if piece is Horizontal and position-Below is not past the boundary or is empty 					
    then return true 

	if piece is Vertical 
		then return false

function Can-Move-Left(piece) returns if we can move to the left
	position-Left ← Get-Position-Left (piece)
	if piece is Horizontal
		then return false 

	if piece is Vertical and position-Left is not past the boundary or is empty 					
    then return true 



function Can-Move-Right(piece) returns if we can move to the right
	position-Right ← Get-Position-Right (piece)
	if piece is Horizontal and position-Right is past the boundary or is empty 					
    then return true 

	if piece is Vertical and position-Right is not past the boundary or is empty 					
    then return true



Time Complexity
As each Can-Move function has to use a function from Board to check if that particular position is not occupied, 
it requires O(n) time to iterate over the ArrayList. Also, a note that in our implementation, empty (‘+’) and 
blocked (‘B’) are considered pieces along with Horizontal and Vertical.

Thus, as we have to search the entire Board (upper-bound) for the particular type of Piece, calling the Can-Move 
function each time, it would lead us to a O(n^2) complexity.
