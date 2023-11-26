package blackjack.controller

import blackjack.model.card.CardDeck
import blackjack.model.game.BlackjackGame
import blackjack.model.player.Dealer
import blackjack.model.player.Player
import blackjack.model.strategy.RandomStrategy
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController {
    fun start() {
        val cards = CardDeck(RandomStrategy().shuffle())
        val players = InputView.inputPlayers().map { player ->
            Player(player, InputView.inputBettingAmount(player))
        }

        val game = BlackjackGame(dealer = Dealer(), players = players, cards = cards)
        game.initDraw(OutputView::printInitStatus)
        game.play(InputView::inputPlayerChoice, OutputView::printPlayerCards)
        game.playDealer(OutputView::printDealerPop)
        game.result(OutputView::printResult, OutputView::printMatchResult, OutputView::printBettingResult)
    }
}

fun main() {
    BlackjackController().start()
}
