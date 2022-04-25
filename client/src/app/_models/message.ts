import { Gsec } from "./gsec";

export interface Message extends Gsec{
    content: string;
    toCustomer: boolean;
    customerId: string;
    employeeId: string;
}