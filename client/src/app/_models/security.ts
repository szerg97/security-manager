export interface Security{
    id?: string;
    faceValue: string;
    denomination: string;
    grossValue: string;
    netValue: string;
    term: string;
    interest: string;
    accruedInterest: string;
    fixedInterest: boolean;
    yield: string;
    referenceYield: string;
    purchased: Date;
    customerId: string;
    distributorId: string;
    categoryId: string;
}