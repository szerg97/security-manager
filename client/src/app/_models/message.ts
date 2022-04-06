import { Gsec } from "./gsec";

export interface Message extends Gsec{
    content: string;
    toCustomer: boolean;
    sent: Date;
    customerId: string;
    employeeId: string;
}