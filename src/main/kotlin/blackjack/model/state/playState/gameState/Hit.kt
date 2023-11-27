package blackjack.model.state.playState.gameState

import blackjack.model.card.Card
import blackjack.model.card.CardDeck
import blackjack.model.state.State
import blackjack.model.state.playState.Running

class Hit(override val cardDeck: CardDeck) : Running() {

    override fun draw(card: Card): State {
        cardDeck.add(card)
        return when {
            cardDeck.isBlackJack() -> BlackJack(cardDeck)
            cardDeck.isBust() -> Bust(cardDeck)
            else -> Hit(cardDeck)
        }
    }

    override fun stay(): State {
        return Stay(cardDeck)
    }

    override fun isBust(): Boolean {
        return false
    }

    override fun isBlackJack(): Boolean {
        return false
    }

    override fun isStay(): Boolean {
        return false
    }

    override fun calculateScore(): Int {
        return cardDeck.calculateScore()
    }

    override fun cards(): List<Card> {
        return cardDeck.deck
    }
}
