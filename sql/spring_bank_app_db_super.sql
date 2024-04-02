create trigger before_fixed_deposit_details_check_amount
    before insert
    on fixed_deposit_details
    for each row
begin
    if NEW.AMOUNT < 0 then
        signal sqlstate '45000' set message_text = 'Amount cannot be negative';
    end if;
    if NEW.AMOUNT > (select BALANCE_AMOUNT from bank_account_details where ACCOUNT_ID = NEW.ACCOUNT_ID) then
        signal sqlstate '45000' set message_text = 'Amount cannot be greater than balance';
    end if;
end;

create trigger after_fixed_deposit_details
    after insert
    on fixed_deposit_details
    for each row
begin
    update bank_account_details set BALANCE_AMOUNT = BALANCE_AMOUNT - NEW.AMOUNT where ACCOUNT_ID = NEW.ACCOUNT_ID;
end;