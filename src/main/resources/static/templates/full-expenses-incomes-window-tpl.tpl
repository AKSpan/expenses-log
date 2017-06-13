<div class="center-block__info-block header-window-block">
    <div class="header-window-block__back-wrapper">
        <input name="back-button" value="" title="Back" type="button"
               class="info-row__action-button">
    </div>
    <select class="header-window-block__choose-period" title="choose period">
        <option value="DAY">День</option>
        <option value="WEEK">Неделя</option>
        <option value="MONTH">Месяц</option>
        <option value="YEAR">Год</option>
        <option value="PERIOD">Период</option>
    </select>
    <div class="header-window-block__info-graphic-wrapper">
        <input name="info-graphic-button" value="" title="Show info" type="button"
               class="info-row__action-button">
    </div>
</div>
<div class="center-block__choosing-period-wrapper center-block__info-block">
    <div class="choosing-period__previous-value-wrapper">
        <input name="prev-value" value="" title="Previous" type="button"
               class="info-row__action-button">
    </div>
    <p class="choose-balance-year__current-value-first-wrapper"><%= firstPeriodValue %></p>
    <p class="choose-balance-year__current-value-second-wrapper" hidden></p>
    <div class="choosing-period__next-value-wrapper">
        <input name="next-value" value="" title="Next" type="button"
               class="info-row__action-button">
    </div>
</div>
<div class="center-block__total-balance-cost-wrapper center-block__info-block">
    <p class="total-balance-cost__text-row">Всего:</p>
    <p class="total-balance-cost__value-row"><%= totalCost %></p>
</div>
<div class="center-block__balance-cost-per-category-wrapper center-block__info-block">
</div>
