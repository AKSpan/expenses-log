<div class="center-block__legend-hover-block"></div>
<legend align="center">Сумма</legend>
<div class="info-block__info-row">
    <input type="button" class="info-row__calc-img info-row__action-button"/>
    <input class="info-row__summary-cost-row" type="text" placeholder="Summary" value="<%= summary %>"
           onkeyup="this.value=this.value.replace(/[^\d*\.\d*]/,'')"/>
</div>