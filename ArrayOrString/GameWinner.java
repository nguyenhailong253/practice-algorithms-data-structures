/**
 * Wendy and Bob play a game where white or black pieces are represented by a
 * string, called "colors". The string "colors" can be arranged as follows:
 * 
 * - Wendy moves first and then they take alternate turns
 * 
 * - With each move. Wendy may remove a white piece that has 2 adjacent white
 * pieces on either side
 * 
 * - Likewise, with each move, Bob may remove any black piece that has 2
 * adjacent black pieces on either side
 * 
 * - After a piece is removed, the string is reduced in size by one piece. For
 * instance: if a piece Y has adjacent pieces X and Y on either side, following
 * a move, X and Z will now be adjacent.
 * 
 * - When a player can no longer move, they have lost the game.
 * 
 * Example
 * 
 * colors = wwwbbbbwww
 * 
 * - Wendy removes the piece w at index 1, colors = wwbbbbwww
 * 
 * - Bob removes the piece b at index 3, colors = wwbbbwww
 * 
 * - Wendy removes the piece w at index 6, colors = wwbbbww
 * 
 * - Bob removes the piece b from index 3, colors = wwbbww
 * 
 * - Wendy has no other move, and Bob wins.
 * 
 * Determine who wins if Wendy and Bob both play with optimum skill
 */

// Pseudo code

/**
 * Test cases
 * 
 * wwbb -> bob
 * 
 * ww -> bob
 * 
 * wwwbb -> wendy
 * 
 * www -> wendy
 * 
 * bbb -> bob
 * 
 * wwbbwwbb -> bob
 * 
 * wwwwbbb -> wendy
 */

/**
 * Brute force O(n^2)
 */

// String input = "wwwwbbb"
// boolean isWendyTurn = true;
// var winner = null;
// while winner is not null:
// char tobeRemoved = isWendyTurn ? 'w' :'b'
// int indexToBeRemoved = find3ConsecutiveSimilarLetters(input)
// if indexToBeRemoved == -1:
// if isWendyTurn: winner = "bob"
// else: winner = "wendy"
// else:
// input = removeCharacterGivenIndex(indexToBeRemoved)
// isWendyTurn = !isWendyTurn

/**
 * O(n)
 */

// boolean isWendyTurn = true
// wendyArray = []
// bobArray = []
// int consecutiveSimilarCharCount = 0
// for index from 0 to input.length:
// if input[index] == input[index - 1]:
// consecutiveSimilarCharCount += 1
// if consecutiveSimilarCharCount >= 3:
// isWendyTurn ? wendyArray.add(index - 1) : bobArray.add(index - 1)
// else:
// consecutiveSimilarCharCount = 1

// return wendyArray.length > bobArray.length ? "wendy" : "bob"
