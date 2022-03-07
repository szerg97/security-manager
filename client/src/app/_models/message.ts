export interface Message{
    id?: string;
    content: string;
    toCustomer: boolean;
    sent: Date;
    customerId: string;
    employeeId: string;
}