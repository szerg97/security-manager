import { Gsec } from "./gsec";

export interface Transaction extends Gsec{
    denomination: string;
    grossValue: string;
    netValue: string;
    term: string;
    accruedInterest: string;
    yield: string;
    referenceYield: string;
    porfolioId: string;
    issuerId: string;
    securityId: string;
}