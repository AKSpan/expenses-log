<div class="center-block__info-block balance-window">
    <div class="balance-window__back-wrapper"><input name="back-button" value="" title="Back" type="button"
                                                     class="info-row__action-button"></div>
    <p>Баланс за год</p>
</div>
<div class="center-block__choose-balance-year-wrapper center-block__info-block">
    <div class="choose-balance-year__prev-year-wrapper">
        <input name="prev-year" value="" title="Previous Year" type="button"
               class="info-row__action-button">
    </div>
    <p class="choose-balance-year__current-year-wrapper"><%= currentYear %></p>
    <div class="choose-balance-year__next-year-wrapper">
        <input name="next-year" value="" title="Next Year" type="button"
               class="info-row__action-button">
    </div>
</div>
<div class="center-block__total-balance-cost-wrapper center-block__info-block">
    <p class="total-balance-cost__text-row">Всего:</p>
    <p class="total-balance-cost__value-row"><%= totalCost %></p>
</div>
<div class="center-block__balance-cost-by-month-wrapper center-block__info-block">
</div>