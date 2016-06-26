private TextField firstName = new TextField("First name");
private TextField lastName = new TextField("Last name");
private TextField email = new TextField("Email");
private NativeSelect status = new NativeSelect("Status");
private PopupDateField birthdate = new PopupDateField("Birthday");
private Button save = new Button("Save");
private Button delete = new Button("Delete");
private CustomerService service = CustomerService.getInstance();
private Customer customer;
private MyUI myUI;

public CustomerForm(MyUI myUI) {
    this.myUI = myUI;
    setSizeUndefined();
    HorizontalLayout buttons = new HorizontalLayout(save, delete);
    buttons.setSpacing(true);
    addComponents(firstName, lastName, email, status, birthdate, buttons);
}

CustomerForm form = new CustomerForm(this);
HorizontalLayout main = new HorizontalLayout(grid, form);
main.setSpacing(true);
main.setSizeFull();
grid.setSizeFull();
main.setExpandRatio(grid, 1);
layout.addComponents(filtering, main);
status.addItems(CustomerStatus.values());
save.setStyleName(ValoTheme.BUTTON_PRIMARY);
save.setClickShortcut(KeyCode.ENTER);

public void setCustomer(Customer customer) {
    this.customer = customer;
    BeanFieldGroup.bindFieldsUnbuffered(customer, this);
    // Show delete button for only customers already in the database
    delete.setVisible(customer.isPersisted());
    setVisible(true);
    firstName.selectAll();
}

save.addClickListener(e->this.save());
delete.addClickListener(e->this.delete());
